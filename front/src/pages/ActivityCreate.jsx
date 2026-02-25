// src/pages/ActivityCreate.jsx
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useCreateActivity } from "../hooks/useCreateActivity";
import { employeeService } from "../services/employeeService"; // hook/service za zaposlene
import { projectService } from "../services/projectService"; // hook/service za projekte

const ActivityCreate = () => {
  const navigate = useNavigate();
  const createActivity = useCreateActivity();

  const [formData, setFormData] = useState({
    description: "",
    time_of_activity: "",
    employee_id: "",
    project_id: "",
  });

  const [employees, setEmployees] = useState([]);
  const [projects, setProjects] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");

  // Fetch employees and projects for dropdowns
  useEffect(() => {
    const fetchData = async () => {
      try {
        const empRes = await employeeService.getAll(); // vrati listu zaposlenih
        setEmployees(empRes);

        const projRes = await projectService.getAll(); // vrati listu projekata
        setProjects(projRes);
      } catch (err) {
        console.error("Error fetching employees/projects:", err);
      }
    };
    fetchData();
  }, []);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErrorMessage("");

    if (
      !formData.description ||
      !formData.time_of_activity ||
      !formData.employee_id ||
      !formData.project_id
    ) {
      setErrorMessage("All fields are required");
      return;
    }

    try {
      await createActivity.mutateAsync(formData);
      navigate("/activities");
    } catch (err) {
      console.error(err);
      setErrorMessage("Failed to create activity. Please try again.");
    }
  };

  return (
    <div className="max-w-xl mx-auto bg-white p-6 rounded shadow mt-6">
      <h1 className="text-2xl font-bold mb-6">Create Activity</h1>

      {errorMessage && (
        <div className="bg-red-50 border border-red-200 text-red-700 px-4 py-3 rounded mb-4">
          {errorMessage}
        </div>
      )}

      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block mb-1 font-medium">Employee</label>
          <select
            name="employee_id"
            value={formData.employee_id}
            onChange={handleChange}
            className="w-full border rounded px-3 py-2"
            required
          >
            <option value="">Select employee</option>
            {employees.map((emp) => (
              <option key={emp.id} value={emp.id}>
                {emp.name} ({emp.email})
              </option>
            ))}
          </select>
        </div>

        <div>
          <label className="block mb-1 font-medium">Project</label>
          <select
            name="project_id"
            value={formData.project_id}
            onChange={handleChange}
            className="w-full border rounded px-3 py-2"
            required
          >
            <option value="">Select project</option>
            {projects.map((proj) => (
              <option key={proj.id} value={proj.id}>
                {proj.name} (ID: {proj.id})
              </option>
            ))}
          </select>
        </div>

        <div>
          <label className="block mb-1 font-medium">Description</label>
          <input
            type="text"
            name="description"
            value={formData.description}
            onChange={handleChange}
            className="w-full border rounded px-3 py-2"
            required
          />
        </div>

        <div>
          <label className="block mb-1 font-medium">Time of Activity</label>
          <input
            type="datetime-local"
            name="time_of_activity"
            value={formData.time_of_activity}
            onChange={handleChange}
            className="w-full border rounded px-3 py-2"
            required
          />
        </div>

        <div className="flex justify-between items-center mt-4">
          <button
            type="button"
            onClick={() => navigate(-1)}
            className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400 transition"
          >
            Back
          </button>

          <button
            type="submit"
            className={`px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition ${
              createActivity.isLoading ? "opacity-50 cursor-not-allowed" : ""
            }`}
            disabled={createActivity.isLoading}
          >
            {createActivity.isLoading ? "Creating..." : "Create"}
          </button>
        </div>
      </form>
    </div>
  );
};

export default ActivityCreate;
