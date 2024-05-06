window.addEventListener('DOMContentLoaded', (event) => {
    document.getElementById('profileForm').addEventListener('submit', function(event) {
        event.preventDefault();
        var formData = new FormData(this);

        fetch('/profile', {
            method: 'PATCH',
            body: formData
        }).then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();
        }).then(text => {
            console.log('Success:', text);
            // Optionally, handle redirection or UI updates here
        }).catch(error => {
            console.error('Error:', error);
        });
    });
});