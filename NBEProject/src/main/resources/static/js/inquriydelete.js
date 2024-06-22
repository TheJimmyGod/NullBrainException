function deleteContact(contactId) {
    if (confirm('정말 삭제하시겠습니까?')) {
        window.location.href = `/admin/inquirydeleteOk?id=${contactId}`;
    }
}