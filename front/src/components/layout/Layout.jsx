import { Outlet } from "react-router-dom";
import Sidebar from "./Sidebar";
import Header from "./Header";

const Layout = () => {
  return (
    <div className="min-h-screen bg-gray-100">
      <div className="print:hidden">
        <Sidebar />
      </div>

      <div className="ml-64 flex flex-col min-h-screen">
        <div className="print:hidden">
          <Header />
        </div>

        <main className="pt-20 p-8">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default Layout;
