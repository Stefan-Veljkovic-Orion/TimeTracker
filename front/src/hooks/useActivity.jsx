import { useQuery } from "@tanstack/react-query";
import { activityService } from "../services/activityService";

export const useActivity = (id) => {
  return useQuery({
    queryKey: ["activity", id],
    queryFn: () => activityService.getActivityById(id),
    enabled: !!id,
  });
};
