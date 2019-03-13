function initFullCalendar(self) {
    var allData = new Map();
    var isRequest = false;// 区分是否请求过数据
    var isFinishRequest = false;
    var timeStamp1, timeStamp2, date1, date2;//双击事件判断的变量
    $("#calendar").fullCalendar({
        header: {
            left: 'prev,next today title',
            center: '',
            right: 'month,agendaWeek,agendaDay'
        },
        height: 490,
        lang: 'zh-cn',
        firstDay: 0,
        allDaySlot: true,
        allDayText: true,
        businessHours: true,
        selectable: true,
        selectHelper: true,
        editable: true,
        dayRender: function (date, cell) {
            // 如果为空，查询一下本页面的数据。装载完成后在viewRender中清空。
            if (!isRequest) {
                isRequest = true;
                $.get(contextPath+"/TimeSetting/getCurrentPageData.koala", {"date": date.format()}, function (result) {
                    if (result.length > 0) {
                        for (var i = 0; i < result.length; i++) {
                            //alert(result[i].date);
                            allData.put(result[i].date, result[i]);
                        }
                    }
                    isFinishRequest = true;
                });
            }
            //已经请求
            var interval = setInterval(function () {
                if (isFinishRequest) {
                    var data = allData.get(date.format());
                    var content = "";
                    var startTime = "";
                    var endTime = "";
                    var conTag = "";
                    var style = "padding:3px;color:#999999";
                    if (data != null) {
                        if (data.dayType == 2) {
                            content = "非工作日";
                            style = "padding:3px;color:#6495ED";
                        } else if (data.dayType == 3) {
                            content = "非工作日";
                            style = "padding:3px;color:#76EE00";
                        } else if (data.dayType == 4) {
                            content = "非工作日";
                            style = "padding:3px;color:#228B22";
                        } else {
                            content = "工作日";
                            startTime = data.startTime.substring(11, 16);
                            endTime = data.endTime.substring(11, 16);
                            conTag = "-";
                            style = "padding:3px;color:#333333;font-weight:800;";
                        }
                    } else {
                        content = "工作日";
                        startTime = "09:00";
                        endTime = "17:00";
                        conTag = "-";
                    }
                    cell.html("<div style='" + style + "'>" + content + "</div><div style='" + style + "'>" + startTime + conTag + endTime + "</div>");
                    //绑定双击事件
                    cell.bind('dblclick', function () {
                        showTimeSetting(self);
                    });
                    clearInterval(interval);
                }
            }, 500);
        },
        viewRender: function (view, element) {
            // 清空缓存，翻月的时候保证会重新查询
            allData = new Map();
            isRequest = false;
            isFinishRequest = false;
        },
        dayClick: function (date, jsEvent, view) {
            if (timeStamp1 == null) {
                //第一次点击
                timeStamp1 = jsEvent.timeStamp;
                date1 = date.format();
            } else {
                timeStamp2 = jsEvent.timeStamp;
                date2 = date.format();
                //如果日期不一样了，把这次的点击作为第一次
                if (date1 != date2) {
                    timeStamp1 = jsEvent.timeStamp;
                    date1 = date.format();
                    return;
                }
                //如果和上一次间隔过大，把这次的点击作为第一次
                if (timeStamp2 - timeStamp1 > 400) {
                    timeStamp1 = jsEvent.timeStamp;
                    date1 = date.format();
                    return;
                } else {
                    this.trigger('dblclick');
                }
                timeStamp1 = null;
                timeStamp2 = null;
                date1 = null;
                date2 = null;
            }
        },
        select: function (start, end, jsEvent, view) {
            $("#dateHidden").val(start.format());
        }
    });
}
function showTimeSetting(self) {
    self.add($(this));
}