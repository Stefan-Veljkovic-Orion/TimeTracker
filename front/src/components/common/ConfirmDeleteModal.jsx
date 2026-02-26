const ConfirmDeleteModal = ({ onConfirm, onCancel }) => {
  return (
    <div className="modal">
      <p>Are you sure you want to delete?</p>

      <button onClick={onConfirm}>Yes</button>
      <button onClick={onCancel}>Cancel</button>
    </div>
  );
};

export default ConfirmDeleteModal;
