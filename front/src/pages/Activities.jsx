import { useState } from "react";
import { useActivities } from "../hooks/useActivities";
import ActivitiesTable from "../components/activities/ActivitiesTable";
import ActivityFilter from "../components/activities/ActivityFilter";
import LoadingSpinner from "../components/common/LoadingSpinner";
import ActivityReport from "../components/reports/ActivityReport";
import { useNavigate } from "react-router-dom";
import { useDeleteActivity } from "../hooks/useDeleteActivity";
import jsPDF from "jspdf";
import autoTable from "jspdf-autotable";
import logo from "../assets/Orion_Innovation_Logo.jpg";

const getTodayDate = () => {
  const today = new Date();
  const year = today.getFullYear();
  const month = String(today.getMonth() + 1).padStart(2, "0");
  const day = String(today.getDate()).padStart(2, "0");
  return `${year}-${month}-${day}`;
};

const Activities = () => {
  const navigate = useNavigate();
  const [selectedDate, setSelectedDate] = useState(getTodayDate());
  const { data: activities = [], isLoading, error } = useActivities();
  const deleteActivity = useDeleteActivity();

  const handleDelete = async (id) => {
    try {
      await deleteActivity.mutateAsync(id);
    } catch (err) {
      console.error(err);
    }
  };
  const handleDownloadPdf = () => {
    const doc = new jsPDF();

    const generatedAt = new Date().toLocaleString();

    const totalActivities = filteredActivities.length;

    const uniqueEmployees = new Set(
      filteredActivities.map((a) => a.employee?.id),
    ).size;

    const uniqueProjects = new Set(filteredActivities.map((a) => a.project?.id))
      .size;

    // Activities per employee
    const activitiesPerEmployee = {};

    filteredActivities.forEach((activity) => {
      const name = activity.employee?.name || "Unknown";

      if (!activitiesPerEmployee[name]) {
        activitiesPerEmployee[name] = 0;
      }

      activitiesPerEmployee[name]++;
    });

    // Activities per project
    const activitiesPerProject = {};

    filteredActivities.forEach((activity) => {
      const project = activity.project?.projectName || "Unknown";

      if (!activitiesPerProject[project]) {
        activitiesPerProject[project] = 0;
      }

      activitiesPerProject[project]++;
    });

    // ===== HEADER =====

    const reportDate = selectedDate;

    // LOGO
    doc.addImage(logo, "JPEG", 14, 10, 35, 15);

    // DESNI HEADER
    doc.setFontSize(16);
    doc.setFont("helvetica", "bold");
    doc.text("Orion TimeTracker", 200, 16, { align: "right" });

    doc.setFontSize(10);
    doc.setFont("helvetica", "normal");
    doc.text(`Report Date: ${reportDate}`, 200, 22, { align: "right" });
    doc.text(`Generated: ${generatedAt}`, 200, 28, { align: "right" });

    // linija ispod headera
    doc.setDrawColor(200);
    doc.line(14, 35, 196, 35);

    // naslov reporta
    doc.setFontSize(20);
    doc.setFont("helvetica", "bold");
    doc.text("Company Activity Report", 14, 50);

    doc.setFontSize(11);
    doc.setFont("helvetica", "normal");
    doc.text(`Report Date: ${selectedDate}`, 14, 60);
    doc.text(`Generated: ${generatedAt}`, 14, 66);

    // ===== SUMMARY =====

    doc.setFontSize(14);
    doc.setFont("helvetica", "bold");
    doc.text("Summary", 14, 80);

    doc.setFontSize(11);
    doc.setFont("helvetica", "normal");

    doc.text(`Total Activities: ${totalActivities}`, 14, 90);
    doc.text(`Employees involved: ${uniqueEmployees}`, 14, 96);
    doc.text(`Projects involved: ${uniqueProjects}`, 14, 102);

    // ===== ACTIVITIES PER EMPLOYEE =====

    let y = 120;

    doc.setFontSize(14);
    doc.setFont("helvetica", "bold");
    doc.text("Activities per Employee", 14, y);

    doc.setFontSize(11);
    doc.setFont("helvetica", "normal");

    Object.entries(activitiesPerEmployee).forEach(([name, count]) => {
      y += 6;
      doc.text(`${name}: ${count}`, 14, y);
    });

    // ===== ACTIVITIES PER PROJECT =====

    y += 12;

    doc.setFontSize(14);
    doc.setFont("helvetica", "bold");
    doc.text("Activities per Project", 14, y);

    doc.setFontSize(11);
    doc.setFont("helvetica", "normal");

    Object.entries(activitiesPerProject).forEach(([name, count]) => {
      y += 6;
      doc.text(`${name}: ${count}`, 14, y);
    });

    // ===== TABLE DATA =====

    const tableData = filteredActivities.map((activity, index) => [
      index + 1,
      activity.employee?.name || "—",
      activity.employee?.email || "—",
      activity.project?.projectName || "—",
      activity.project?.id || "—",
      activity.time
        ? new Date(activity.time).toLocaleTimeString([], {
            hour: "2-digit",
            minute: "2-digit",
          })
        : "—",
      activity.description || "—",
    ]);

    autoTable(doc, {
      startY: y + 15,

      head: [
        [
          "#",
          "Employee",
          "Email",
          "Project",
          "Project ID",
          "Time",
          "Description",
        ],
      ],

      body: tableData,

      styles: {
        fontSize: 10,
        cellPadding: 3,
      },

      headStyles: {
        fillColor: [33, 150, 243],
        textColor: 255,
        fontStyle: "bold",
      },

      alternateRowStyles: {
        fillColor: [245, 245, 245],
      },

      didDrawPage: (data) => {
        const pageCount = doc.internal.getNumberOfPages();

        doc.setFontSize(10);

        doc.text(
          `Page ${doc.internal.getCurrentPageInfo().pageNumber} of ${pageCount}`,
          data.settings.margin.left,
          doc.internal.pageSize.height - 10,
        );

        doc.text(
          "Generated by Employee Activity System",
          doc.internal.pageSize.width - 80,
          doc.internal.pageSize.height - 10,
        );
      },
    });

    doc.save(`employee-activity-report-${selectedDate}.pdf`);
  };

  const filteredActivities = activities.filter((activity) => {
    if (!selectedDate) return true;

    const activityDate = activity.time?.split("T")[0];
    return activityDate === selectedDate;
  });
  console.log(activities);

  return (
    <div>
      <div className="flex flex-col lg:flex-row lg:justify-between lg:items-center gap-4 mb-6 print:hidden">
        <h1 className="text-2xl font-bold text-gray-900">
          Employees activities
        </h1>

        <div className="flex flex-col sm:flex-row gap-3">
          <button
            onClick={() => navigate("/activities/create")}
            className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md shadow-sm transition"
          >
            Create Activity
          </button>
          <button className="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-md shadow-sm transition">
            Generate data
          </button>

          <button
            onClick={handleDownloadPdf}
            className="bg-gray-700 hover:bg-gray-800 text-white px-4 py-2 rounded-md shadow-sm transition"
          >
            Download as PDF
          </button>
        </div>
      </div>

      <div className="print:hidden">
        <ActivityFilter value={selectedDate} onChange={setSelectedDate} />
      </div>

      {isLoading && <LoadingSpinner />}
      {error && (
        <div className="bg-red-50 border border-red-200 text-red-700 px-4 py-3 rounded">
          Loading error
        </div>
      )}

      {!isLoading && !error && (
        <ActivitiesTable
          activities={filteredActivities}
          onDelete={handleDelete}
        />
      )}

      {/* Aktivnost report za print */}
      {!isLoading && !error && (
        <div id="printableReport" className="hidden print:block">
          <ActivityReport
            activities={activities || []}
            selectedDate={selectedDate}
          />
        </div>
      )}
    </div>
  );
};

export default Activities;
