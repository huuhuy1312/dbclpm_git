import React, { useEffect, useState } from "react";
import "../css/viewDetailsInvoice.css"
import { useParams } from "react-router-dom";
import axios from 'axios';
const ViewDetailsInvoice = () => {
  const {id} = useParams();
  const [invoice, setInvoice] = useState(null);
  const [customer,setCustomer] = useState(null);
  const [address,setAddress] = useState(null);
  const [formula,setFormula] = useState(null);
  function formatPriceToVND(price) {
    return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
    }).format(price);
  }
 
  const showDetails = (sonuoc) => {
    const bangGia = [
        formula?.price_smaller_or_equal_to_10m3,
        formula?.price_from_10m3_to_20m3,
        formula?.price_from_20m3_to_30m3,
        formula?.price_greater_or_equal_30m3
    ];
    
    let result = [];
    let count = 0;
    
    while (sonuoc > 0 && count < 4) {
        let tmp = count < 3 ? Math.min(sonuoc, 10) : sonuoc;
        result.push(
            <tr key={count}>
                <td>{count}</td>
                <td>{tmp}</td>
                <td>{formatPriceToVND(bangGia[count])}</td>
                <td>{formatPriceToVND(tmp * bangGia[count])}</td>
            </tr>
        );
        sonuoc -= tmp;
        count++;
    }

    return result;
};

  const getDetailsInvoiceById = async () => {
    try {
        const response = await fetch(`http://127.0.0.1:8080/api/invoice/${id}`, {
            method: "GET"
        });
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        const data = await response.json();
        console.log(data)
        setInvoice(data.invoice);
        setCustomer(data.customer)
        setFormula(data.formula)
        setAddress(data.customer.address)
        
    } catch (error) {
        console.error(error);
        throw error;
    }
  }
  
  useEffect(()=>{
    getDetailsInvoiceById();
  },[])
  const thanhToan = async (amount,customer_id, new_water_index,invoiceId) => {
    try {
        const response = await axios.post(`http://127.0.0.1:8080/api/payment/create_payment/${amount *100}/${customer_id}/${new_water_index}/${invoiceId}`);
        console.log(response);
        const url = response.data.url;
        window.location.href = url; 
    } catch (error) {
        console.error(error);
        throw error;
    }
}
  useEffect(()=>{
    console.log(customer);
  },[customer])
  return (
    <div className="root-view">
      <div className="contains-hdn">
        <div className="title-hdn">Hoá Đơn Nước</div>
        <div className="info_customer">
        <div className="column1">
          <div className="column1-info">
            <div>Số danh bộ: </div>
            <div>{customer?.ordinal_numbers}</div>
          </div>
          <div className="column1-info">
            <div>Tên khách hàng: </div>
            <div>{customer?.infoCommon.name}</div>
          </div>
          <div className="column1-info">
            <div>Địa chỉ:</div>
            <div>{`${address?.detailsAddress},${address?.wards},${address?.district},${address?.provinceOrCity}`}</div>
          </div>
          <div className="column1-info">
            <div>Email:</div>
            <div>{customer?.infoCommon.email}</div>
          </div>
          <div className="column1-info">
            <div>Số điện thoại:</div>
            <div>{customer?.infoCommon.phoneNumber}</div>
          </div>
        </div>
        <div className="column2">
        <div className="column2-info">
              <div>Chỉ số cũ:</div>
              <div>{invoice?.oldNumber}</div>
            </div>
            <div className="column2-info">
              <div>Chỉ số mới:</div>
              <div>{invoice?.newNumber}</div>
            </div>
            <div className="column2-info">
              <div>Số nước:</div>
              <div>{invoice?.newNumber - invoice?.oldNumber}</div>
            </div>
            <div className="column2-info">
              <div>Đối tượng: </div>
              <div>{customer?.residentialType.name}</div>
            </div>
            <div className="column2-info">
              <div>Trạng thái: </div>
              <div>{invoice?.status}</div>
            </div>
        </div>
      </div>
      </div>
      
      
      <div className="table_cal">
        <table className="tbl-1">
          <thead>
            <tr>
              <td>Bậc</td>
              <td>Số nước</td>
              <td>Đơn giá</td>
              <td>Thành tiền</td>
            </tr>
          </thead>
          <tbody>
            {showDetails(invoice?.newNumber - invoice?.oldNumber)}
          </tbody>
        </table>
        <div class="vax_details">
          <div style={{display:"flex"}}>
            <div className="vax_detail_1">{`Phí bảo vệ môi trường( ${formula?.bvmtTax}% ): `}</div>
            <div className="vax_detail_2">{formatPriceToVND(invoice?.totalNoTax*formula?.bvmtTax/100)}</div>
          </div>
          <div style={{display:"flex"}}>
            <div  className="vax_detail_1">{`VAT( ${formula?.vatTaxPer}% ): `}</div>
            <div  className="vax_detail_2">{formatPriceToVND(invoice?.totalNoTax*formula?.vatTaxPer/100)}</div>
          </div>
          <div style={{display:"flex"}}>
            <div  className="vax_detail_1">Tổng tiền cần thanh toán : </div>
            <div className="vax_detail_2">{formatPriceToVND(invoice?.totalAll)}</div>
          </div>
        </div>
      </div>
      <button className="btn-tt" onClick={()=>thanhToan(invoice?.totalAll,customer?.id, invoice?.newNumber, invoice?.id)}>Thanh toán</button>
    </div>
  );
};
export default ViewDetailsInvoice;