window.onload = function() {
    var today = new Date();
    var yyyy = today.getFullYear();
    var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
    var dd = String(today.getDate()).padStart(2, '0');
    var hh = String(today.getHours()).padStart(2, '0');
    var min = String(today.getMinutes()).padStart(2, '0');

    var todayDate = yyyy + '-' + mm + '-' + dd + " "+ hh + ':' + min;
    document.getElementById('date').value = todayDate;
};


    document.getElementById('file1').addEventListener('change', handleFileSelect);
    document.getElementById('file2').addEventListener('change', handleFileSelect);

    function handleFileSelect(event) {
    const fileList = document.getElementById('fileList');
    const input = event.target;
    const file = input.files[0];

    if (file) {
    const fileItem = document.createElement('div');
    fileItem.className = 'file-item';
    fileItem.innerHTML = `<span>${file.name}</span><button type="button" class="remove-btn">X</button>`;

    fileItem.querySelector('.remove-btn').addEventListener('click', function() {
    input.value = '';  // Clear the input
    fileList.removeChild(fileItem);  // Remove the file item from the list
});

    fileList.appendChild(fileItem);
}
}
