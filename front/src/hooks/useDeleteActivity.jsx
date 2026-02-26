import { useMutation, useQueryClient } from "@tanstack/react-query";
import { activityService } from "../services/activityService";

export const useDeleteActivity = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (id) => activityService.deleteActivity(id),

    onSuccess: () => {
      queryClient.invalidateQueries(["activities"]);
    },
  });
};
