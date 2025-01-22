import React, {useEffect, useState} from "react";
import {
    LineChart,
    Tooltip,
    Line,
    CartesianGrid,
    XAxis,
    YAxis,
    ResponsiveContainer,
} from "recharts";
import {getOverview} from "../../../../../../services/new/instructor/instructor/overview-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {formatRupiah, formatRupiahWithoutSymbol} from "../../../../../../utils/new-utils.js";

export const Charts = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [monthlyIncomes, setMonthlyIncomes] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getInstructorOverview = await getOverview();

                if (getInstructorOverview.success) {
                    const monthlyIncomesData = getInstructorOverview.data.overview.finance.monthly_incomes;

                    const formattedData = monthlyIncomesData.map(item => ({
                        name: `${item.month_name} ${item.year}`,
                        value: item.income,
                    }));

                    setMonthlyIncomes(formattedData);
                }

                setIsLoading(false);
            }
            catch (error) {
                console.error(error);

                if (error.message) {
                    let msg = error.message;
                    if (error.data.error) {
                        msg += ' : ' + error.data.error;
                    }

                    await withReactContent(Swal).fire({
                        title: 'Terjadi Kesalahan!',
                        text: msg,
                        icon: 'error',
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        })
                }
            }
        }

        fetchInitialData();
    }, []);

    const CustomTooltip = ({ active, payload }) => {
        if (active && payload && payload.length) {
            return (
                <div className="custom-tooltip">
                    <p className="label">{`${payload[0].payload.name}`}</p>
                    <p className="intro">{`${formatRupiah(payload[0].value)}`}</p>
                </div>
            );
        }

        return null;
    };

    const chart = (interval) => (
        <ResponsiveContainer height={250} width="100%">
            <LineChart data={monthlyIncomes}>
                <CartesianGrid strokeDasharray="" />
                <XAxis tick={{ fontSize: 12 }} dataKey="name" interval={interval} />
                <YAxis
                    tick={{ fontSize: 12 }}
                    domain={[0, 300]}
                    tickCount={7}
                    interval={interval}
                    tickFormatter={formatRupiahWithoutSymbol}
                />
                <Tooltip content={<CustomTooltip />} />
                <Line
                    type="monotone"
                    dataKey="value"
                    strokeWidth={2}
                    stroke="#336CFB"
                    fill="#336CFB"
                    activeDot={{ r: 8 }}
                />
            </LineChart>
        </ResponsiveContainer>
    );

    return (
        <>
            {
                isLoading && (
                    <>
                        <span>
                            <i className={'fa fa-spinner fa-spin mr-5'}></i>
                            <strong role="status">Loading...</strong>
                        </span>
                    </>
                )
            }

            {
                !isLoading && chart("preserveEnd")
            }
        </>
    );
};
