import React, { forwardRef } from "react";

const ActivityReport = forwardRef(({ activities, selectedDate }, ref) => {
  return (
    <div
      ref={ref}
      className="bg-white p-6"
      style={{
        width: "210mm",
        minHeight: "297mm",
        margin: "0 auto",
        boxSizing: "border-box",
      }}
    >
      <h1 className="text-2xl font-bold mb-2">Employees Activities</h1>
      <p className="text-sm mb-4">Date: {selectedDate}</p>

      <div className="overflow-x-auto">
        <table className="min-w-full divide-y divide-gray-200 border border-gray-300">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border">
                #
              </th>
              <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border">
                Employee Name
              </th>
              <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border">
                Employee Email
              </th>
              <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border">
                Project Name
              </th>
              <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border">
                Project ID
              </th>
              <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border">
                Time of Activity
              </th>
              <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border">
                Description
              </th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {activities.map((activity, idx) => (
              <tr key={activity.id} className="hover:bg-gray-50">
                <td className="px-4 py-2 text-sm text-gray-900 border">
                  {idx + 1}
                </td>
                <td className="px-4 py-2 text-sm text-gray-900 border">
                  {activity.employee?.name || "—"}
                </td>
                <td className="px-4 py-2 text-sm text-gray-900 border">
                  {activity.employee?.email || "—"}
                </td>
                <td className="px-4 py-2 text-sm text-gray-900 border">
                  {activity.project?.name || "—"}
                </td>
                <td className="px-4 py-2 text-sm text-gray-900 border">
                  {activity.project?.id || "—"}
                </td>
                <td className="px-4 py-2 text-sm text-gray-900 border">
                  {new Date(activity.time_of_activity).toLocaleTimeString([], {
                    hour: "2-digit",
                    minute: "2-digit",
                  })}
                </td>
                <td className="px-4 py-2 text-sm text-gray-900 border">
                  {activity.description || "—"}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
});

export default ActivityReport;
