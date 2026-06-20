import { Link, Outlet, useLocation } from "react-router-dom";

export default function Layout() {
  const location = useLocation();

  return (
    <div className="flex h-screen bg-gray-100">
      {/* Sidebar */}
      <div className="w-64 bg-white shadow-md p-6">
        <h2 className="text-2xl font-bold text-blue-600 mb-8">CareerVia</h2>

        <nav className="space-y-3">
          <Link
            to="/dashboard"
            className={`block p-3 rounded-xl ${
              location.pathname === "/dashboard"
                ? "bg-blue-100 text-blue-600"
                : "hover:bg-gray-100"
            }`}
          >
            Dashboard
          </Link>

          <Link
            to="/jobs"
            className={`block p-3 rounded-xl ${
              location.pathname === "/jobs"
                ? "bg-blue-100 text-blue-600"
                : "hover:bg-gray-100"
            }`}
          >
            Jobs
          </Link>
        </nav>
      </div>

      {/* Main Section */}
      <div className="flex-1 flex flex-col">
        {/* Header */}
        <div className="bg-white px-6 py-4 shadow-sm flex justify-between items-center">
          <h1 className="text-lg font-semibold capitalize">
            {location.pathname.replace("/", "")}
          </h1>

          <div className="w-10 h-10 bg-blue-600 text-white flex items-center justify-center rounded-full">
            JD
          </div>
        </div>

        {/* Page Content */}
        <div className="flex-1 p-6 overflow-y-auto w-full">
  <Outlet />
</div>
      </div>
    </div>
  );
}
