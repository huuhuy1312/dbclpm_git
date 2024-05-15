import React, { useEffect, useState } from "react";
import "../css/searchInvoice.css";
import { useNavigate } from 'react-router-dom';
import { Link } from "react-router-dom";
const SearchInvoice = () => {
  const [customer, setCustomer] = useState(null);
  const [customer_id, setCustomerID] = useState(null);
  const [address, setAddress] = useState(null);
  const [listInvoice, setListInvoice] = useState(null);
  const navigate = useNavigate();
    useEffect(() => {
        const getUserInfoFromCookie = () => {
            const cookies = document.cookie.split(';');
            for (let cookie of cookies) {
                const [name, value] = cookie.trim().split('=');
                if (name === 'userInfo') {
                    console.log(JSON.parse(value).id)
                    setCustomerID(JSON.parse(value).id)
                    return JSON.parse(value);
                }
            }
            return null;
        };
        const userData = getUserInfoFromCookie();
        console.log(userData)
        if (!userData) {
            navigate('/');
        }
    }, [navigate]);
  const getCustomerById = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/customer/${customer_id}`,
        {
          method: "GET",
        }
      );
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const data = await response.json();
      const addressTmp = data.address;
      setAddress(
        `${addressTmp.detailsAddress},${addressTmp.wards},${addressTmp.district},${addressTmp.provinceOrCity}`
      );
      setCustomer(data);
      return data; // Trả về dữ liệu lấy được từ API
    } catch (error) {
      console.error(error);
      throw error; // Ném lỗi ra bên ngoài để xử lý ở phần gọi hàm này
    }
  };
  const getInvoiceByCusId = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/invoice/getByCustomer/${customer_id}`,
        {
          method: "GET",
        }
      );
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const data = await response.json();
      console.log(data);
      setListInvoice(data);
    } catch (error) {
      console.error(error);
      throw error; // Ném lỗi ra bên ngoài để xử lý ở phần gọi hàm này
    }
  };
  function formatPriceToVND(price) {
    return new Intl.NumberFormat("vi-VN", {
      style: "currency",
      currency: "VND",
    }).format(price);
  }
  useEffect(() => {
    if(customer_id!==null)
    {
      getCustomerById();
      getInvoiceByCusId();
    }
  }, [customer_id]);
  function convertAndAdjustTime(isoTime) {
    const timeObj = new Date(isoTime);
    timeObj.setHours(timeObj.getHours() + 7);
    const formattedTime = `${timeObj.getMonth() + 1}/${timeObj.getFullYear()}`;

    return formattedTime;
  }
  return (
    <div className="root-search">
      <div className="contains">
        <div>
          <div className="title-page">Danh sách hoá đơn</div>
          <div className="thanhcat"></div>
        </div>
        <div className="search-contains">
          <div className="info-cus-form">
            <div className="info-cus-title">Thông tin khách hàng</div>
            <div className="info-cus-content">
              <div className="info-cus-item">
                <div className="info-cus-item1">Số danh bộ :</div>
                <div className="info-cus-item2">{customer?.id}</div>
              </div>
              <div className="info-cus-item">
                <div className="info-cus-item1">Khách Hàng :</div>
                <div className="info-cus-item2">{customer?.infoCommon.name}</div>
              </div>
              <div className="info-cus-item">
                <div className="info-cus-item1">Địa chỉ :</div>
                <div className="info-cus-item2"> {address}</div>
              </div>
              <div className="info-cus-item">
                <div className="info-cus-item1">Số diện thoại :</div>
                <div className="info-cus-item2">{customer?.infoCommon.phoneNumber}</div>
              </div>
              <div className="info-cus-item">
                <div className="info-cus-item1">Email :</div>
                <div className="info-cus-item2">{customer?.infoCommon.email}</div>{" "}
              </div>
            </div>
          </div>
        </div>
        <div className="table-invoice">
          <div className="title-invoice">Danh sách hoá đơn</div>
          <table>
            <thead>
              <tr>
                <td>Kỳ</td>
                <td>Chỉ số mới</td>
                <td>Chỉ số cũ</td>
                <td>Tiêu thụ</td>
                <td>Tiền nước</td>
                <td>Tổng thuế</td>
                <td>Tổng cộng</td>
                <td></td>
              </tr>
            </thead>
            <tbody>
            {listInvoice?.map((item) => (
                item.status !== "Đã Thanh Toán" ? (
                  <tr key={item.id}>
                    <td>{convertAndAdjustTime(item.period)}</td>
                    <td>{item.newNumber}</td>
                    <td>{item.oldNumber}</td>
                    <td>{item.newNumber - item.oldNumber}</td>
                    <td>{formatPriceToVND(item.totalNoTax)}</td>
                    <td>{formatPriceToVND(item.totalTax)}</td>
                    <td>{formatPriceToVND(item.totalAll)}</td>
                    <td>
                      <a href={`viewDetailsInvoice/${item.id}`}>Xem chi tiết</a>
                    </td>
                  </tr>
                ) : null
              ))}

            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};
export default SearchInvoice;
