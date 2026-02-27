import { useMutation, useQueryClient } from "@tanstack/react-query";
import { aiService } from "../services/aiService";

export const useGenerateEmployees = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: aiService.generateEmployees,

    onSuccess: () => {
      queryClient.invalidateQueries(["employees"]);
    },
  });
};
