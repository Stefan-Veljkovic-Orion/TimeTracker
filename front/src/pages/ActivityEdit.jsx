import { useParams, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { useActivity } from "../hooks/useActivity";
import { useUpdateActivity } from "../hooks/useUpdateActivity";
import { useDeleteActivity } from "../hooks/useDeleteActivity";

const ActivityEdit = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const { data: activity, isLoading } = useActivity(id);
  const updateMutation = useUpdateActivity();
  const deleteMutation = useDeleteActivity();

  const [formData, setFormData] = useState({
    description: "",
    time_of_activity: "",
  });

  useEffect(() => {
    if (activity) {
      setFormData({
        description: activity.description || "",
        time_of_activity: activity.time_of_activity
          ? activity.time_of_activity.slice(0, 16)
          : "",
      });
    }
  }, [activity]);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleUpdate = async () => {
    try {
      await updateMutation.mutateAsync({
        id,
        data: formData,
      });

      navigate("/activities");
    } catch (err) {
      console.error("Update failed", err);
    }
  };

  const handleDelete = async () => {
    if (!window.confirm("Are you sure you want to delete this activity?"))
      return;

    try {
      await deleteMutation.mutateAsync(id);
      navigate("/activities");
    } catch (err) {
      console.error("Delete failed", err);
    }
  };

  if (isLoading) return <div>Loading...</div>;

  if (!activity)
    return <div className="text-red-600 font-medium">Activity not found.</div>;

  return (
    <div className="max-w-xl mx-auto bg-white p-6 rounded shadow">
      <h1 className="text-2xl font-bold mb-4">Edit Activity</h1>

      <div className="mb-4">
        <label className="block mb-1 font-medium">Description</label>
        <input
          type="text"
          name="description"
          value={formData.description}
          onChange={handleChange}
          className="w-full border rounded px-3 py-2"
        />
      </div>

      <div className="mb-4">
        <label className="block mb-1 font-medium">Time</label>
        <input
          type="datetime-local"
          name="time_of_activity"
          value={formData.time_of_activity}
          onChange={handleChange}
          className="w-full border rounded px-3 py-2"
        />
      </div>

      <div className="flex justify-between">
        <button
          onClick={() => navigate(-1)}
          className="px-4 py-2 bg-gray-300 rounded"
        >
          Back
        </button>

        <div className="flex gap-3">
          <button
            onClick={handleDelete}
            className="px-4 py-2 bg-red-600 text-white rounded"
          >
            Delete
          </button>

          <button
            onClick={handleUpdate}
            className="px-4 py-2 bg-blue-600 text-white rounded"
            disabled={updateMutation.isLoading}
          >
            {updateMutation.isLoading ? "Saving..." : "Save"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default ActivityEdit;
