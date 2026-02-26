import { Menu } from "lucide-react";

const Header = ({ openSidebar }) => {
  const userInitials = "M";

  return (
    <header
      className="bg-gray-900 h-16 flex items-center px-4 md:px-6
      fixed top-0 left-0 lg:left-64 right-0 z-10
      border-b border-gray-700"
    >
      {/* hamburger mobile */}
      <button onClick={openSidebar} className="lg:hidden mr-4 text-white">
        <Menu size={24} />
      </button>

      <div className="flex-1" />

      <div className="flex items-center gap-3">
        <div className="w-9 h-9 rounded-full bg-blue-600 flex items-center justify-center text-white font-medium text-sm">
          {userInitials}
        </div>

        <span className="text-sm font-medium text-gray-200 hidden sm:block">
          Manager
        </span>
      </div>
    </header>
  );
};

export default Header;
