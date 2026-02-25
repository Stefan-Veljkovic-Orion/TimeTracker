import { useState } from "react";
import { useActivities } from "../hooks/useActivities";
import ActivitiesTable from "../components/activities/ActivitiesTable";
import ActivityFilter from "../components/activities/ActivityFilter";
import LoadingSpinner from "../components/common/LoadingSpinner";

const getTodayDate = () => {
  const today = new Date();
  const year = today.getFullYear();
  const month = String(today.getMonth() + 1).padStart(2, "0");
  const day = String(today.getDate()).padStart(2, "0");
  return `${year}-${month}-${day}`;
};

const Activities = () => {
  const [selectedDate, setSelectedDate] = useState(getTodayDate());
  const { data: activities, isLoading, error } = useActivities(selectedDate);

  const handlePrint = () => window.print();
  return (
    <div>
      <div className="flex justify-between items-center mb-6 print:hidden">
        <h1 className="text-2xl font-bold text-gray-900">
          Employees activities
        </h1>

        <div className="flex gap-3">
          <button
            onClick={() => navigate("/activities/create")}
            className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md shadow-sm transition"
          >
            Create Activity
          </button>

          <button
            onClick={handlePrint}
            className="bg-gray-700 hover:bg-gray-800 text-white px-4 py-2 rounded-md shadow-sm transition"
          >
            Download as PDF
          </button>
        </div>
      </div>
      <div className="print:hidden">
        <ActivityFilter value={selectedDate} onChange={setSelectedDate} />
      </div>
      {isLoading && <LoadingSpinner />}
      {error && (
        <div className="bg-red-50 border border-red-200 text-red-700 px-4 py-3 rounded">
          Loading error
        </div>
      )}
      {!isLoading && !error && (
        <ActivitiesTable activities={activities || []} />
      )}
    </div>
  );
};

export default Activities;
