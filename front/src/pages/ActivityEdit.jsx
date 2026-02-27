import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { employeeService } from "../services/employeeService";
import { projectService } from "../services/projectService";
import { activityService } from "../services/activityService";

const ActivityEdit = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    description: "",
    time_of_activity: "",
    employee_id: "",
    project_id: "",
  });

  const [employees, setEmployees] = useState([]);
  const [projects, setProjects] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");

  // fetch employees, projects i activity
  useEffect(() => {
    const fetchData = async () => {
      try {
        const [empRes, projRes, actRes] = await Promise.all([
          employeeService.getAll(),
          projectService.getAll(),
          activityService.getById(id),
        ]);

        setEmployees(empRes);
        setProjects(projRes);

        setFormData({
          description: actRes.description || "",
          time_of_activity: actRes.time?.slice(0, 16) || "",
          employee_id: actRes.employee?.id || "",
          project_id: actRes.project?.id || "",
        });
      } catch (err) {
        console.error(err);
        setErrorMessage("Failed to load activity");
      }
    };

    fetchData();
  }, [id]);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
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

    const payload = {
      employee: { id: Number(formData.employee_id) },
      project: { id: Number(formData.project_id) },
      description: formData.description,
      time: formData.time_of_activity,
      employee_id: Number(formData.employee_id),
      project_id: Number(formData.project_id),
    };

    try {
      await activityService.update(id, payload);
      navigate("/");
    } catch (error) {
      console.error(error);
      setErrorMessage("Failed to update activity");
    }
  };

  return (
    <div className="max-w-xl mx-auto bg-white p-4 md:p-6 rounded shadow mt-6">
      <h1 className="text-2xl font-bold mb-6">Edit Activity</h1>

      {errorMessage && (
        <div className="bg-red-50 border border-red-200 text-red-700 px-4 py-3 rounded mb-4">
          {errorMessage}
        </div>
      )}

      <form onSubmit={handleSubmit} className="space-y-4">
        {/* Employee */}
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

        {/* Project */}
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
                {proj.projectName}
              </option>
            ))}
          </select>
        </div>

        {/* Description */}
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

        {/* Time */}
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

        <div className="flex justify-between mt-4">
          <button
            type="button"
            onClick={() => navigate(-1)}
            className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
          >
            Back
          </button>

          <button
            type="submit"
            className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
          >
            Update Activity
          </button>
        </div>
      </form>
    </div>
  );
};

export default ActivityEdit;
