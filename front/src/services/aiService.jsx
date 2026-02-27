import { apiClient } from "../api/client";

export const aiService = {
  generateEmployees: async () => {
    const res = await apiClient.post("/ai/employees/generate");
    return res.data;
  },

  generateActivities: async () => {
    const res = await apiClient.post("/ai/activities/generate");
    return res.data;
  },
};
