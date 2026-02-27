import { useState } from "react";
import { Outlet } from "react-router-dom";
import Sidebar from "./Sidebar";
import Header from "./Header";

const Layout = () => {
  const [sidebarOpen, setSidebarOpen] = useState(false);

  return (
    <div className="min-h-screen bg-gray-100 flex">
      {/* Sidebar desktop */}
      <div className="hidden lg:block">
        <Sidebar />
      </div>

      {/* Sidebar mobile */}
      {sidebarOpen && (
        <div className="fixed inset-0 z-40 flex md:hidden">
          <div
            className="fixed inset-0 bg-black/40"
            onClick={() => setSidebarOpen(false)}
          />

          <div className="relative z-50">
            <Sidebar closeSidebar={() => setSidebarOpen(false)} />
          </div>
        </div>
      )}

      {/* Content */}
      <div className="flex-1 lg:ml-64 flex flex-col min-h-screen">
        <Header openSidebar={() => setSidebarOpen(true)} />

        <main className="pt-20 px-4 md:px-8 pb-8">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default Layout;
