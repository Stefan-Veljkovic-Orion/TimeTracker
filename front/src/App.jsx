import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Layout from "./components/layout/Layout";
import Activities from "./pages/Activities";
import ActivityEdit from "./pages/ActivityEdit";
import ActivityCreate from "./pages/ActivityCreate";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Activities />} />
          <Route path="/activities/:id" element={<ActivityEdit />} />
          <Route path="/activities/create" element={<ActivityCreate />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
