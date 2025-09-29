-- 2 role: nhà bán hàng (đăng nhập, đăng sản phẩm, quản lý sản phẩm (sửa, xóa sản phẩm), chuyên trạng thái đơn hàng (chuyển thành đã giao), xuất thống kê - bấm vào nút để xuất thống kê thông tin mua hàng)
-- người mua hàng: đăng ký, đăng nhập, đặt đơn, theo dõi thông tin đơn, 
create table users(
	id bigserial primary key,
	name varchar(255) unique not null,
    phone_number varchar(255) unique,
	password varchar(255),
	address varchar(255),
    role varchar(20) check(role in ('customer', 'trader'))
);
create table products(
	id bigserial primary key,
    name varchar(255),
    price bigint,
    description text,
    product_available int
);
create table product_image(
    id bigserial primary key,
    product_id bigint,
    link_image varchar(255),
    foreign key (product_id) references product(id)
);
create table orders(
    order_id varchar(255),
    customer_id bigint, 
    pay_method varchar(255),
    order_status varchar(50) check (order_status in ('ordered', 'shipping', 'delivered')),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    foreign key (customer_id) references users(id)
);
create table order_product(
    order_id bigint,
    product_id bigint,
    quantity int not null default 1,
    primary key (order_id, product_id), 
    foreign key (order_id) references orders(order_id),
    foreign key (product_id) references products(id)
);
-- tạo db -> tạo view với tạo backend xử lý đồng thời. theo domain và 
-- theo giao diện
-- ->cho xác thực qua OAuth;