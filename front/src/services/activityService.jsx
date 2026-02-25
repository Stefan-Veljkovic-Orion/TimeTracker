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
  // GET by date
  getActivitiesByDate: async (date) => {
    return new Promise((resolve) => {
      setTimeout(() => {
        const filtered = MOCK_ACTIVITIES.filter((act) => act.date === date);
        resolve(filtered);
      }, 300);
    });
  },

  // GET by id
  getActivityById: async (id) => {
    return new Promise((resolve) => {
      setTimeout(() => {
        const activity = MOCK_ACTIVITIES.find((act) => act.id === Number(id));
        resolve(activity);
      }, 300);
    });
  },

  // UPDATE (kasnije zameni sa activityApi.update)
  updateActivity: async (id, data) => {
    return activityApi.update({ id, data });
  },

  // DELETE
  deleteActivity: async (id) => {
    return activityApi.delete(id);
  },

  // CREATE
  createActivity: async (data) => {
    try {
      const res = await activityApi.create(data);
      return res;
    } catch (err) {
      console.error("Error creating activity:", err);
      throw err;
    }
  },
};
