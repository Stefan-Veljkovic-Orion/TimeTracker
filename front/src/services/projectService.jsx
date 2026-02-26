// src/services/projectService.js
import { apiClient } from "../api/client";

export const projectService = {
  // GET all projects
  getAll: async () => {
    const response = await apiClient.get("/projects");
    return response.data;
  },

  // GET project by id (opciono)
  getById: async (id) => {
    const response = await apiClient.get(`/projects/${id}`);
    return response.data;
  },
};
