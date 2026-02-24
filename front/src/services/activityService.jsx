import React from "react";

const MOCK_ACTIVITIES = [
  {
    id: 1,
    employeeId: 1,
    employeeName: "Petar Petrovic",
    projectId: 1,
    projectName: "Web aplikacija",
    date: "2026-02-23",
    time: "09:00",
    description: "Daily",
  },
  {
    id: 2,
    employeeId: 2,
    employeeName: "Milan Milic",
    projectId: 1,
    projectName: "Web aplikacija",
    date: "2026-02-23",
    time: "10:00",
    description: "CodeReview",
  },
  {
    id: 3,
    employeeId: 1,
    employeeName: "Petar Petrovic",
    projectId: 2,
    projectName: "Mobile aplikacija",
    date: "2026-02-24",
    time: "12:00",
    description: "Implementacija login forme",
  },
  {
    id: 4,
    employeeId: 3,
    employeeName: "Ana Matic",
    projectId: 2,
    projectName: "Mobile aplikacija",
    date: "2026-02-24",
    time: "14:00",
    description: "Testiranje login forme",
  },
];
export const activityService = async (date) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      const filtered = MOCK_ACTIVITIES.filter((act) => act.date === date);
      resolve(filtered);
    }, 300);
  });
};
