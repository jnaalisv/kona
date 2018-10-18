import React from 'react'

const Notifications = ({ notifications }) => (
    <div className="notification-container">
        <style jsx>{`
div.notification-container {

}

div.info-notification {
    color: white;
    background-color: green;
    width: 20vw;
}

div.error-notification {
    color: white;
    background-color: red;
    width: 20vw;
}    `}</style>
        {Object.keys(notifications).map(key => {
            const notification = notifications[key];
            return <div key={key} className={`${notification.type}-notification`}>{notification.message}</div>
        })}
    </div>
);

export default Notifications;
