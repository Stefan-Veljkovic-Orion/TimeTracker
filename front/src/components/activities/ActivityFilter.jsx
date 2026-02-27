import PropTypes from "prop-types";

const ActivityFilter = ({ value, onChange }) => {
  return (
    <div className="flex items-center gap-4 mb-6">
      <label htmlFor="date-filter" className="font-medium text-gray-700">
        Datum:
      </label>
      <input
        id="date-filter"
        type="date"
        value={value}
        onChange={(e) => onChange(e.target.value)}
        className="px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2
        focus:ring-blue-500 focus:border-blue-500"
      />
    </div>
  );
};

ActivityFilter.propTypes = {
  value: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
};

export default ActivityFilter;
