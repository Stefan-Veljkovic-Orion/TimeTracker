import { Fragment } from "react";
import {
  Menu,
  MenuButton,
  MenuItem,
  MenuItems,
  Transition,
} from "@headlessui/react";
import { User } from "lucide-react";

const Header = () => {
  const userInitials = "M";
  return (
    <header
      className="bg-gray-900 h-16 flex items-center px-6 ml-64 fixed top-0 right-0 left-0 z-10
    border-b border-gray-700"
    >
      <div className="flex-1 flex-items-center" />

      <div className="flex items-center gap-3">
        <div
          className="w-9 h-9 rounded-full bg-blue-600 flex items-center justify-center text-white font-medium
         text-sm"
        >
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
