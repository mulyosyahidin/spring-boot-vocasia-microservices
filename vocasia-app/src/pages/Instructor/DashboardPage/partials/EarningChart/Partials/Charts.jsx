import React from "react";
import {
    LineChart,
    Tooltip,
    Line,
    CartesianGrid,
    XAxis,
    YAxis,
    ResponsiveContainer,
} from "recharts";
import { data } from "../data.js";

export const Charts = () => {
    const chart = (interval) => (
        <ResponsiveContainer height={250} width="100%">
            <LineChart data={data}>
                <CartesianGrid strokeDasharray="" />
                <XAxis tick={{ fontSize: 12 }} dataKey="name" interval={interval} />
                <YAxis
                    tick={{ fontSize: 12 }}
                    domain={[0, 300]}
                    tickCount={7}
                    interval={interval}
                />
                <Tooltip />
                <Line
                    type="monotone"
                    dataKey="value"
                    strokeWidth={2}
                    stroke="#336CFB"
                    fill="#336CFB"
                    activeDot={{ r: 8 }}
                />
                {/* <Line type="monotone" dataKey="uv" stroke="#82ca9d" /> */}
            </LineChart>
        </ResponsiveContainer>
    );

    return (
        <>
            {chart("preserveEnd")}
            {/* {chart('preserveStart')}
      {chart('preserveStartEnd')}
      {chart('equidistantPreserveStart')}
      {chart(1)} */}
        </>
    );
};
