import { NavLink } from "react-router-dom";
import { Calendar, Users, Briefcase, FolderTree } from "lucide-react";

const navigation = [
  { name: "Activity", href: "/", icon: Calendar },
  { name: "Employees", href: "/employees", icon: Users },
  { name: "Projects", href: "/projects", icon: Briefcase },
  { name: "Departments", href: "/departments", icon: FolderTree },
];

const Sidebar = () => {
  return (
    <div className="w-64 bg-gray-900 text-white h-screen fixed left-0 top-0">
      <div className="p-4 text-xl font-bold">Time Tracker</div>
      <nav className="mt-8">
        {navigation.map((item) => (
          <NavLink
            key={item.name}
            to={item.href}
            className={({ isActive }) =>
              `flex items-center gap-3 px-4 py-3 hover:bg-gray-800 
          transition ${isActive ? "bg-gray-800 border-l-4 border-blue-500" : ""}`
            }
          >
            <item.icon size={20} />
            {item.name}
          </NavLink>
        ))}
      </nav>
    </div>
  );
};

export default Sidebar;
