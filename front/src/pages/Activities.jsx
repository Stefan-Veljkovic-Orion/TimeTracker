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

  return (
    <div>
      <h1 className="text-2xl font-bold text-gray-900 mb-6">
        Aktivnosti zaposlenih
      </h1>
      <ActivityFilter value={selectedDate} onChange={setSelectedDate} />
      {isLoading && <LoadingSpinner />}
      {error && (
        <div className="bg-red-50 border border-red-200 text-red-700 px-4 py-3 rounded">
          Greska pri ucitavanju aktivnosti
        </div>
      )}
      {!isLoading && !error && (
        <ActivitiesTable activities={activities || []} />
      )}
    </div>
  );
};

export default Activities;
