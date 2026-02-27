import apiClient from "./client";

export const getProjects = () => {
  return apiClient.get("/projects");
};
