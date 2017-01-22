import React from 'react'

const Notifications = ({notifications}) => (
    <div className="notification-container">
        {Object.keys(notifications).map(key => {
            const notification = notifications[key];
            return <div key={key} className={`${notification.type}-notification`}>{notification.message}</div>
        })}
    </div>
);

export default Notifications;

// TODO: propTypes errors [] required