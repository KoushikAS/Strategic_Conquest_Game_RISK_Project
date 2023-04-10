import React, { createContext, useState, useMemo } from 'react';

export const OrderContext = createContext();

export const OrderProvider = ({ children }) => {
    const [orders, setOrders] = useState([]);

    const addOneOrder = (newOrder) => {
        setOrders((prevOrders) => [...prevOrders, newOrder]);
    };

    const addManyOrders = (newOrders) => {
        setOrders((prevOrders) => [...prevOrders, ...newOrders]);
    };

    const removeOrder = (orderId) => {
        const updatedOrders = orders.filter((order) => order.id !== orderId);
        setOrders(updatedOrders);
    };

    const value = useMemo(
        () => ({
            orders,
            addOneOrder,
            addManyOrders,
            removeOrder,
        }),
        [orders]
    );

    return (
        <OrderContext.Provider value={value}>
            {children}
        </OrderContext.Provider>
    );
};