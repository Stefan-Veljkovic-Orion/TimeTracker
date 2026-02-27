import { useMutation, useQueryClient } from "@tanstack/react-query";
import { aiService } from "../services/aiService";

export const useGenerateActivities = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: aiService.generateActivities,

    onSuccess: () => {
      queryClient.invalidateQueries(["activities"]);
    },
  });
};
