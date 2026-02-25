import { useQuery } from "@tanstack/react-query";
import { activityService } from "../services/activityService";

export const useActivities = (date) => {
  return useQuery({
    queryKey: ["activities", date],
    queryFn: () => activityService.getActivitiesByDate(date),
    enabled: !!date,
    staleTime: 5 * 60 * 1000,
  });
};
