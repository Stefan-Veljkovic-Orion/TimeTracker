import apiClient from "./client";

export const getEmployees = () => {
  return apiClient.get("/employees");
};
