import { activityApi } from "../api/activityApi";
import { apiClient } from "../api/client";

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
