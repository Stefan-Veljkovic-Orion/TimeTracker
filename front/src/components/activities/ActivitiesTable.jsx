import PropTypes from "prop-types";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import ConfirmDeleteModal from "../common/ConfirmDeleteModal";

const ActivitiesTable = ({ activities, onDelete }) => {
  const navigate = useNavigate();

  const [selectedId, setSelectedId] = useState(null);

  const openDeleteModal = (e, id) => {
    e.stopPropagation();
    setSelectedId(id);
  };

  const closeModal = () => {
    setSelectedId(null);
  };

  const confirmDelete = () => {
    if (onDelete && selectedId) {
      onDelete(selectedId);
    }
    closeModal();
  };

  if (!activities || activities.length === 0) {
    return (
      <div className="text-center py-8 text-gray-500">
        Nema aktivnosti za odabrani datum
      </div>
    );
  }

  return (
    <>
      <div className="overflow-x-auto bg-white rounded-lg shadow">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                #
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                Employee Name
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                Employee Email
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                Project Name
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                Project ID
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                Time of Activity
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                Description
              </th>
              <th className="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase">
                Actions
              </th>
            </tr>
          </thead>

          <tbody className="bg-white divide-y divide-gray-200">
            {activities.map((activity, idx) => (
              <tr
                key={activity.id}
                onClick={() => navigate(`/activities/${activity.id}`)}
                className="hover:bg-gray-50 cursor-pointer"
              >
                <td className="px-6 py-4 text-sm">{idx + 1}</td>

                <td className="px-6 py-4 text-sm">
                  {activity.employee?.name || "—"}
                </td>

                <td className="px-6 py-4 text-sm">
                  {activity.employee?.email || "—"}
                </td>

                <td className="px-6 py-4 text-sm">
                  {activity.project?.projectName || "—"}
                </td>

                <td className="px-6 py-4 text-sm">
                  {activity.project?.id || "—"}
                </td>

                <td className="px-6 py-4 text-sm">
                  {activity.time
                    ? new Date(activity.time).toLocaleTimeString([], {
                        hour: "2-digit",
                        minute: "2-digit",
                      })
                    : "—"}
                </td>

                <td className="px-6 py-4 text-sm">
                  {activity.description || "—"}
                </td>

                <td
                  className="px-6 py-4 text-sm text-right space-x-3"
                  onClick={(e) => e.stopPropagation()}
                >
                  <button
                    onClick={() => navigate(`/activities/${activity.id}`)}
                    className="text-blue-600 hover:text-blue-800 font-medium"
                  >
                    Edit
                  </button>

                  <button
                    onClick={(e) => openDeleteModal(e, activity.id)}
                    className="text-red-600 hover:text-red-800 font-medium"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {selectedId && (
        <ConfirmDeleteModal onConfirm={confirmDelete} onCancel={closeModal} />
      )}
    </>
  );
};

ActivitiesTable.propTypes = {
  activities: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number.isRequired,
      employee: PropTypes.shape({
        id: PropTypes.number,
        name: PropTypes.string,
        email: PropTypes.string,
      }),
      project: PropTypes.shape({
        id: PropTypes.number,
        name: PropTypes.string,
      }),
      time_of_activity: PropTypes.string.isRequired,
      description: PropTypes.string,
    }),
  ).isRequired,
  onDelete: PropTypes.func,
};

export default ActivitiesTable;
