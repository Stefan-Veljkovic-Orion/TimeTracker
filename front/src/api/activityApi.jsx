import { apiClient } from "./client";

export const activityApi = {
  // GET /activities?date=2026-02-25 - primer
  getByDate: async (date) => {
    const response = await apiClient.get("/activities", {
      params: { date },
    });
    return response.data;
  },

  // GET /activities/:id - primer
  getById: async (id) => {
    const response = await apiClient.get(`/activities/${id}`);
    return response.data;
  },

  // POST /activities - primer
  create: async (data) => {
    const response = await apiClient.post("/activities/create", data);
    return response.data;
  },

  // PUT /activities/:id - primer
  update: async ({ id, data }) => {
    const response = await apiClient.put(`/activities/${id}`, data);
    return response.data;
  },

  // DELETE /activities/:id - primer
  delete: async (id) => {
    const response = await apiClient.delete(`/activities/${id}`);
    return response.data;
  },
};
