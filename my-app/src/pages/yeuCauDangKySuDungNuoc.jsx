import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "../css/ycdksdn.css";
import Loader from "./components/loader";

const YeuCauDangKySuDungNuoc = () => {
  const [nameFind, setNameFind] = useState(null);
  const [baseNameFind, setBaseNameFind] = useState(null);
  const [residentialNameFind, setResidentialNameFind] = useState(null);
  const [listResult, setListResult] = useState([]);
  const [rejectReason, setRejectReason] = useState("");
  const [showRejectModal, setShowRejectModal] = useState(false);
  const [rejectItemId, setRejectItemId] = useState(null);
  const [notification, setNotification] = useState("");
  const [loading, setLoading] = useState(false);
  const [imageShow, setImageShow] = useState(null);
  const getRequiredRegisterUseWater = async () => {
    try {
      const response = await axios.get(
        `http://127.0.0.1:8080/api/customer/getNonActivedCustomers?${nameFind && `name=${nameFind}`}&${baseNameFind && `baseName=${baseNameFind}`}&${residentialNameFind && `residentialName=${residentialNameFind}`}`
      );
      setListResult(response.data);
      console.log(response.data);
    } catch (error) {
      console.error("Lỗi:", error);
    }
  };
  // useEffect(() => {
  //   if (notification !== "") {
  //     const timeout = setTimeout(() => {
  //       setNotification("");
  //     }, 3000);

  //     return () => clearTimeout(timeout);
  //   }
  // }, [notification]);
  useEffect(() => {
    getRequiredRegisterUseWater();
  }, [notification]);

  const handleConfirm = async (cus_id, full_name, base_id, num_of_customer, email) => {
    // Kiểm tra xem tất cả các tham số đều đã được cung cấp
    if (!cus_id || !full_name || !base_id || !email) {
      console.log("Thông tin trong body trước khi thực hiện yêu cầu:");
    console.log("customer_id:", cus_id);
    console.log("full_name:", full_name);
    console.log("base_id:", base_id);
    console.log("num_of_customer:", num_of_customer);
    console.log("email:", email);
      console.error("Thiếu thông tin, không thể thực hiện yêu cầu.");
      return; // Dừng hàm nếu có thông tin thiếu
    }
  
    // Hiển thị các thông tin sẽ gửi trong body
    console.log("Thông tin trong body trước khi thực hiện yêu cầu:");
    console.log("customer_id:", cus_id);
    console.log("full_name:", full_name);
    console.log("base_id:", base_id);
    console.log("num_of_customer:", num_of_customer);
    console.log("email:", email);
  
    try {
      setLoading(true);
      const response = await axios.post("http://127.0.0.1:8080/api/auth/createUser", {
        full_name: full_name,
        base_id: base_id,
        customer_id: cus_id,
        num_of_customer: num_of_customer,
        email: email
      });
      setNotification(response.data);
      setLoading(false)
    } catch (error) {
      console.error("Lỗi:", error);
    }
  };
  
  const handleReject = async (itemId, reason) => {
    try {
      // Gửi yêu cầu từ chối đến server
      const response = await axios.post("http://127.0.0.1:8080/api/auth/rejectRequest", {
        customer_id: itemId,
        reason: reason,
      });
      setNotification(response.data)
      setShowRejectModal(false);
      getRequiredRegisterUseWater();
    } catch (error) {
      console.error("Lỗi:", error);
    }
  };

  return (
    <div className="root-ycdk">
        {notification && <div className="notification">{notification}</div>}
      <div className="title-ycdk">Các yêu cầu đăng ký sử dụng nước</div>
      <table className="tbl-ycdk">
        <thead>
          <tr>
            <th className="col1">STT</th>
            <th className="col2">Thông tin chi tiết</th>
            <th className="col3">Hành Động</th>
          </tr>
        </thead>
        <tbody>
          {listResult.map((item, index) => (
            <tr key={index}>
              <td className="col1">{index + 1}</td>
              <td className="col2">
                <div className="info-container">
                  <div className="info-user-container">
                    <div className="info1">Họ và tên :</div>
                    <div className="info2">{item?.infoCommon.name}</div>
                  </div>
                  <div className="info-user-container">
                    <div className="info1">Số điện thoại :</div>
                    <div className="info2">{item?.infoCommon.phoneNumber}</div>
                  </div>
                  <div className="info-user-container">
                    <div className="info1">Email :</div>
                    <div className="info2">{item?.infoCommon.email}</div>
                  </div>
                  <div className="info-user-container">
                    <div className="info1">Địa chỉ:</div>
                    <div className="info2">{`${item?.address.detailsAddress},${item?.address.wards},${item?.address.district}.${item?.address.provinceOrCity}`}</div>
                  </div>
                  <div className="info-user-container">
                    <div className="info1">Loại hộ :</div>
                    <div className="info2">{item?.residentialType.name}</div>
                  </div>
                  <div>
                    <div className="image-container" >
                      <div className="img-container" style={{cursor:"pointer"}}>
                        <div className="info-img">Ảnh CCCD/CMT mặt trước :</div>
                        <img style={{width:"80%",height:"80%", objectFit:"contain"}}src={item?.front_image}  onClick={()=>setImageShow(item?.front_image)} alt="Ảnh CCCD/CMT mặt trước" />
                      </div>
                      <div className="img-container" style={{cursor:"pointer"}}>
                        <div className="info-img">Ảnh CCCD/CMT mặt sau :</div>
                        <img style={{width:"80%",height:"80%", objectFit:"contain"}} src={item?.back_image}  onClick={()=>setImageShow(item?.back_image)} alt="Ảnh CCCD/CMT mặt sau" />
                      </div>
                    </div>
                  </div>
                  
                  <div>
                    {item?.certificate_of_poverty ? (
                      <div className="image-container" style={{cursor:"pointer"}}>
                        <div className="img-container">
                          <div>Giấy chứng nhận hộ nghèo:</div>
                          <img style={{width:"80%",height:"80%", objectFit:"contain"}}src={item?.certificate_of_poverty} onClick={()=>setImageShow(item?.certificate_of_poverty)}alt="Giấy chứng nhận hộ nghèo:" />
                        </div>
                      </div>
                    ) : null}
                  </div>
                </div>
              </td>
              <td className="col3">
                <div style={{justifyItems:"center", display:"flex"}}>
                  <button className="btn btn-confirm" onClick={() => { handleConfirm(item?.id, item?.infoCommon.name, item?.base.ordinalNumbers, item?.base.num_of_customer,item?.infoCommon.email) }}>Xác nhận</button>
                  <button className="btn btn-reject" onClick={() => { setRejectItemId(item?.id); setShowRejectModal(true); }}>Từ chối</button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
          {
            loading && <Loader/>
          }
          {
              imageShow&&(
                <div className="loader_contains" onClick={()=>setImageShow(null)}>
                  <div style={{margin:"auto"}}>
                      <img style={{width:"100%", height:"100%"}}src={imageShow}></img>
                  </div>
                </div>
              )
          }
      {showRejectModal && (
        <div className="modal" style={{ display: showRejectModal ? 'block' : 'none' }}>
        <div className="modal-content">
            <span className="close" onClick={() => setShowRejectModal(false)}>×</span>
            <h2>Nhập lý do từ chối</h2>
            <textarea value={rejectReason} onChange={(e) => setRejectReason(e.target.value)}></textarea>
            <button onClick={() => handleReject(rejectItemId, rejectReason)}>Xác nhận</button>
        </div>
        </div>

      )}
    </div>
  );
}
export default YeuCauDangKySuDungNuoc;
