import { useMutation, useQueryClient } from "@tanstack/react-query";
import { activityService } from "../services/activityService";

export const useUpdateActivity = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ id, data }) => activityService.updateActivity(id, data),
    onSuccess: () => {
      queryClient.invalidateQueries(["activities"]);
    },
  });
};
