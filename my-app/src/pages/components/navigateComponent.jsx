import { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faChevronDown, faChevronRight, faGears, faUser } from '@fortawesome/free-solid-svg-icons';
import '../../css/Navigate.css'; // Import file CSS
import { Link } from 'react-router-dom';

const NavigateComponent = () => {
    const [userInfo, setUserInfo] = useState(null);
    const [showManagerDashBoard, setShowManagerDashBoard] = useState(false);
    const [showEmployeeBoard, setShowEmployeeBoard] = useState(false);
    const [showCustomerBoard, setShowCustomerBoard] = useState(false);

    useEffect(() => {
        // Hàm để lấy thông tin người dùng từ cookie
        const getUserInfoFromCookie = () => {
            const cookies = document.cookie.split(';');
            for (let cookie of cookies) {
                const [name, value] = cookie.trim().split('=');
                if (name === 'userInfo') {
                    return JSON.parse(value);
                }
            }
            return null;
        };

        // Gọi hàm để lấy thông tin người dùng từ cookie
        const userData = getUserInfoFromCookie();
        setUserInfo(userData);

        if (userData) {
            setShowManagerDashBoard(userData.role.name === "manager");
            setShowCustomerBoard(userData.role.name === "customer");
            setShowEmployeeBoard(userData.role.name === "employee");
        }
    }, []);
    const [selectedItem, setSelectedItem] = useState(() => {
        // Kiểm tra xem có giá trị đã lưu trong localStorage không
        const savedItem = localStorage.getItem('selectedItem');
        return savedItem ? savedItem : null;
    });

    const handleItemClick = (item) => {
        setSelectedItem(item);
        window.location.href = `/${item}`;
    };

    // Lưu trữ giá trị của selectedItem vào localStorage khi nó thay đổi
    useEffect(() => {
        localStorage.setItem('selectedItem', selectedItem);
    }, [selectedItem]);

    return (
        <div className="navigate-contains">
            <div className="info">
                <div className="info-title"><div style={{paddingLeft:10, fontSize:20}}><FontAwesomeIcon icon={faUser} style={{paddingRight:10}}></FontAwesomeIcon>Đăng nhập</div></div>
                <div className="info-row">
                    <div className="info-label">Mã định danh</div>
                    <div>
                        {showEmployeeBoard && userInfo?.employee.id}
                        {showCustomerBoard && userInfo?.ordinal_numbers}
                        {showManagerDashBoard&& userInfo?.id}
                    </div>
                </div>
                <div className="info-row">
                    <div className="info-label">Họ và tên</div>
                    <div>
                        {showEmployeeBoard && userInfo?.employee.infoCommon.name}
                        {showCustomerBoard && userInfo?.fullName}
                        {showManagerDashBoard&& userInfo?.username}
                    </div>
                </div>
                <div className="info-row" >
                   <Link className="info-row_link" to={"/logout"}>Đăng xuất</Link>
                </div>
            </div>
            <div className="navigate">
                <div className="info-title"><div style={{paddingLeft:10, fontSize:20}}><FontAwesomeIcon icon={faGears} style={{paddingRight:10}}></FontAwesomeIcon>Tính năng</div></div>
                <ul className="navigate-list">
                            <li className={`navigate-item ${selectedItem === 'trang_chu' ? 'selected' : ''}`} onClick={() => handleItemClick('trang_chu')}>
                                <FontAwesomeIcon icon={selectedItem === 'trang_chu'? faChevronRight: faChevronDown}/>
                                <div>Trang chủ</div> 
                            </li>
                    {
                        showEmployeeBoard &&(
                            <li className={`navigate-item ${selectedItem === 'cap_nhat_so_nuoc' ? 'selected' : ''}`} onClick={() => handleItemClick('cap_nhat_so_nuoc')}>
                                <FontAwesomeIcon icon={selectedItem === 'cap_nhat_so_nuoc'? faChevronRight: faChevronDown}/>
                                <div>Cập nhật số nước</div> 
                            </li>
                        )
                    }
                    {
                        showCustomerBoard &&(
                            <li className={`navigate-item ${selectedItem === 'search_invoice' ? 'selected' : ''}`} onClick={() => handleItemClick('search_invoice')}>
                                <FontAwesomeIcon icon={selectedItem === 'search_invoice'? faChevronRight: faChevronDown}/>
                                <div>Danh sách hoá đơn nước</div> 
                            </li>
                        )
                    }
                    {
                        showManagerDashBoard &&(
                            <li className={`navigate-item ${selectedItem === 'duyet_yeu_cau' ? 'selected' : ''}`} onClick={() => handleItemClick('duyet_yeu_cau')}>
                                <FontAwesomeIcon icon={selectedItem === 'duyet_yeu_cau'? faChevronRight: faChevronDown}/>
                                <div>Duyệt yêu cầu đăng ký sử dụng nước</div> 
                            </li>
                        )
                    }
                    
                    {/* Add more li items here if needed */}
                </ul>
            </div>  
        </div>
    );
}

export default NavigateComponent;
