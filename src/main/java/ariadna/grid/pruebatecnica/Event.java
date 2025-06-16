package ariadna.grid.pruebatecnica;

import java.time.LocalDateTime;

public class Event {
	private int id;
	private int sourceId;
	private LocalDateTime ts;
	private double value;
	private Source source;

	public Event() {
	}

	public Event(int id, int sourceId, LocalDateTime ts, double value, Source source) {
		this.id = id;
		this.sourceId = sourceId;
		this.ts = ts;
		this.value = value;
		this.source = source;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public LocalDateTime getTs() {
		return ts;
	}

	public void setTs(LocalDateTime ts) {
		this.ts = ts;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "{id: " + this.id + ", sourceId: " + this.sourceId + ", ts: " + this.ts + ", value: " + this.value
				+ ", source: { sourceId: " + this.source.getId() + ", name: " + this.source.getName() + "}}";
	}

}
