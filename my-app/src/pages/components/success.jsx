import React from "react";
import { useNavigate } from "react-router-dom";
import "../../css/loader.css";
import successImage from "../../image/success.png";

const Success = ({ content }) => {
  const lines = content.split("\n");
  const navigate = useNavigate(); 

  const redirectToLogin = () => {
    if(content=="Thanh Toán thành công")navigate("/trang_chu");
    else navigate("/") 
  };
  return (
    <div className="loader_contains">
      <div style={{ margin: "auto", alignItems: "center", display: "flex", flexDirection: "column" }}>
        <img src={successImage} alt="Success" />
        <div style={{ color: "white", fontSize: 20, width: "100%", textAlign: "center" }}>
          {lines.map((line, index) => (
            <React.Fragment key={index}>
              {line}
              <br />
            </React.Fragment>
          ))}
        </div>
        <button style={{ width: "100%", height: 50, fontSize: 30, marginTop: 30 }} onClick={redirectToLogin}>
          {content === "Thanh Toán thành công" ? "Quay trở lại Trang Chủ" : "Quay trở lại trang Login"}
        </button>

      </div>
    </div>
  );
};

export default Success;
