-- default pemission
insert into permission(id, name, key, created_time)
values('e3f80850-a996-4948-889d-b0a35fb18e20', 'Xem người dùng', 'USER_VIEW', 1652199980979);
insert into permission(id, name, key, created_time)
values('39c85f9f-0c88-4db3-8294-553004b108ca', 'Thêm người dùng', 'USER_CREATE', 1652199980979);
insert into permission(id, name, key, created_time)
values('5a9148f3-fcf3-4ff9-a95d-a8d14365c392', 'Sửa người dùng', 'USER_UPDATE', 1652199980979);
insert into permission(id, name, key, created_time)
values('e457b8f9-54b2-4034-8f37-1382c6229225', 'Xóa người dùng', 'USER_DELETE', 1652199980979);
insert into permission(id, name, key, created_time)
values('a1f80487-ca8e-49cd-8162-0fd4c7f0fdb0', 'Xem vai trò', 'ROLE_VIEW', 1652199980979);
insert into permission(id, name, key, created_time)
values('bd383300-b189-4b80-851e-b919c160c0d3', 'Thêm vai trò', 'ROLE_CREATE', 1652199980979);
insert into permission(id, name, key, created_time)
values('d7eaf307-3d26-452f-b281-4e7e5aaeac7e', 'Sửa vai trò', 'ROLE_UPDATE', 1652199980979);
insert into permission(id, name, key, created_time)
values('8f8745b9-50db-4f13-8290-611e69487e97', 'Xóa vai trò', 'ROLE_DELETE', 1652199980979);

-- role
insert into role(id, name, created_time, created_by, updated_time, updated_by)
values('9ac86bc3-9c73-425e-8f12-e1135fa31e6f', 'Sys admin', 1652199980979, 'ea151d61-67a3-4a11-869e-9b240d69e9bc', null, null);

-- role and permission
insert into role_and_permission(role_id, permission_id)
values('9ac86bc3-9c73-425e-8f12-e1135fa31e6f', 'e3f80850-a996-4948-889d-b0a35fb18e20');
insert into role_and_permission(role_id, permission_id)
values('9ac86bc3-9c73-425e-8f12-e1135fa31e6f', '39c85f9f-0c88-4db3-8294-553004b108ca');
insert into role_and_permission(role_id, permission_id)
values('9ac86bc3-9c73-425e-8f12-e1135fa31e6f', '5a9148f3-fcf3-4ff9-a95d-a8d14365c392');
insert into role_and_permission(role_id, permission_id)
values('9ac86bc3-9c73-425e-8f12-e1135fa31e6f', 'e457b8f9-54b2-4034-8f37-1382c6229225');
insert into role_and_permission(role_id, permission_id)
values('9ac86bc3-9c73-425e-8f12-e1135fa31e6f', 'a1f80487-ca8e-49cd-8162-0fd4c7f0fdb0');
insert into role_and_permission(role_id, permission_id)
values('9ac86bc3-9c73-425e-8f12-e1135fa31e6f', 'bd383300-b189-4b80-851e-b919c160c0d3');
insert into role_and_permission(role_id, permission_id)
values('9ac86bc3-9c73-425e-8f12-e1135fa31e6f', 'd7eaf307-3d26-452f-b281-4e7e5aaeac7e');
insert into role_and_permission(role_id, permission_id)
values('9ac86bc3-9c73-425e-8f12-e1135fa31e6f', '8f8745b9-50db-4f13-8290-611e69487e97');

-- super admin user
insert into app_user(id, created_by, created_time, email, enable, full_name, password, phone_number, updated_by, updated_time, username, role_id)
values('07157a06-d2bb-4912-bfa3-8ab1365b38bd', '07157a06-d2bb-4912-bfa3-8ab1365b38bd', 1652199980979, 'sysadmin@gmail.com', true, 'syadmin', '$2a$10$Hse9SASSJ5GeqQKYzXJLVefbCIq76IC42fqgGigqh1x34TRDQ4SaC
', null, null, null, 'sysadmin', '9ac86bc3-9c73-425e-8f12-e1135fa31e6f')
