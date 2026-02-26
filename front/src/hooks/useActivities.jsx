import { useQuery } from "@tanstack/react-query";
import { activityService } from "../services/activityService";

export const useActivities = () => {
  return useQuery({
    queryKey: ["activities"],
    queryFn: activityService.getAll,
    staleTime: 5 * 60 * 1000,
  });
};
