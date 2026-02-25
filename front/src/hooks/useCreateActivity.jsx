import { useMutation, useQueryClient } from "@tanstack/react-query";
import { activityService } from "../services/activityService";

export const useCreateActivity = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: activityService.createActivity,
    onSuccess: () => {
      queryClient.invalidateQueries(["activities"]);
    },
  });
};
