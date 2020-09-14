-- 20200914
-- 修改workTask唯一性为工作项，时间，类型
ALTER TABLE `workTask`
DROP INDEX `task_unique`,
ADD UNIQUE INDEX `task_unique`(`workDate`, `workItem`, `workType`) USING BTREE COMMENT '工作项，时间，类型唯一';

--添加workSheet表workType字段
ALTER TABLE `worksheet`
ADD COLUMN `workType` int(0) NULL DEFAULT 0 COMMENT '工作类型：0白班、1夜班、2加班' AFTER `mid`;
--修改workSheet表唯一索引
ALTER TABLE `workSheet`
DROP INDEX `sheet_unique`,
ADD UNIQUE INDEX `sheet_unique`(`mid`, `workDate`, `workType`) USING BTREE COMMENT '用户id，日期，工作类型必须唯一';