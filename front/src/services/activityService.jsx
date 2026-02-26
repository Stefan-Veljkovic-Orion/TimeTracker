import { activityApi } from "../api/activityApi";

/*export const activityService = async (date) => {
  const res = await fetch(`/api/activities?date=${date}`);
  const data = await res.json();
  return data;
}; */

const MOCK_ACTIVITIES = [
  {
    id: 1,
    employee: { id: 101, name: "John Doe", email: "john@example.com" },
    project: { id: 201, name: "TimeTracker" },
    time_of_activity: "2026-02-25T09:30:00Z",
    description: "Worked on API integration",
    date: "2026-02-25",
  },
  {
    id: 2,
    employee: { id: 102, name: "Jane Smith", email: "jane@example.com" },
    project: { id: 202, name: "Website Redesign" },
    time_of_activity: "2026-02-25T11:00:00Z",
    description: "UI updates",
    date: "2026-02-25",
  },
];

export const activityService = {
  getActivitiesByDate: async (date) => {
    return await activityApi.getByDate(date);
  },

  getActivityById: async (id) => {
    return await activityApi.getById(id);
  },

  updateActivity: async (id, data) => {
    return await activityApi.update({ id, data });
  },

  deleteActivity: async (id) => {
    return await activityApi.delete(id);
  },

  createActivity: async (data) => {
    return await activityApi.create(data);
  },
  getAll: async () => {
    const res = await apiClient.get("/activities");
    return res.data;
  },
};
