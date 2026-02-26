import { activityApi } from "../api/activityApi";
import { apiClient } from "../api/client";

export const activityService = {
  getActivitiesByDate: async (date) => {
    return await activityApi.getByDate(date);
  },

  getById: async (id) => {
    const res = await apiClient.get("/activities");
    return res.data.find((a) => a.id === Number(id));
  },

  update: async (id, payload) => {
    const res = await apiClient.put(`/activities/${id}`, payload);
    return res.data;
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
