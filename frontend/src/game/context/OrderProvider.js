import React, { createContext, useState, useMemo, useCallback } from 'react';

export const OrderContext = createContext();

export const OrderProvider = ({ children }) => {
    const [orders, setOrders] = useState([]);

    const addOneOrder = useCallback((newOrder) => {
        setOrders((prevOrders) => [...prevOrders, newOrder]);
    }, [])

    const addManyOrders = useCallback((newOrders) => {
        setOrders((prevOrders) => [...prevOrders, ...newOrders]);
    }, [])

    const removeOrder = useCallback((orderId) => {
        const updatedOrders = orders.filter((order) => order.id !== orderId);
        setOrders(updatedOrders);
    }, [orders])

    const value = useMemo(
        () => ({
            orders,
            addOneOrder,
            addManyOrders,
            removeOrder,
        }),
        [orders, addOneOrder, addManyOrders, removeOrder]
    );

    return (
        <OrderContext.Provider value={value}>
            {children}
        </OrderContext.Provider>
    );
};