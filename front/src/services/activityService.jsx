import { activityApi } from "../api/activityApi";

export const activityService = async (date) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      const filtered = MOCK_ACTIVITIES.filter((act) => act.date === date); //promeniti kada se kreira u BE sve sto treba
      resolve(filtered);
    }, 300);
  });
};
