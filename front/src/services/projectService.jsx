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

  // =======================
  // MOCK OPTION (za razvoj bez backend-a)
  // =======================
  /*
  getAll: async () => {
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve([
          { id: 201, name: "TimeTracker" },
          { id: 202, name: "Website Redesign" },
        ]);
      }, 300);
    });
  },

  getById: async (id) => {
    return new Promise((resolve) => {
      setTimeout(() => {
        const projects = [
          { id: 201, name: "TimeTracker" },
          { id: 202, name: "Website Redesign" },
        ];
        resolve(projects.find((p) => p.id === Number(id)));
      }, 300);
    });
  },
  */
};
