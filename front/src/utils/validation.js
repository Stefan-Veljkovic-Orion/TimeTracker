export const validateEmployeeName = (name) => {
  const regex = /^[A-Za-z]+ [A-Za-z]+$/;
  return regex.test(name);
};

export const validateOrionEmail = (email) => {
  const regex = /^[A-Za-z0-9._%+-]+@orioninc\.com$/;
  return regex.test(email);
};
